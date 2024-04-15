package com.laaz.demo.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laaz.demo.repositories.ModuleRepository;
import com.laaz.demo.services.SeederService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.laaz.demo.entities.Module;

@Service
@AllArgsConstructor
public class SeederServiceImpl implements SeederService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void executeSeed() {
        try {
            _seedModules();
        } catch (IOException e) {
            System.err.println("Error seeding modules: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void _seedModules() throws IOException {
        moduleRepository.deleteAll();
        // Try-with-resources to ensure the stream is closed properly
        try (InputStream input = new ClassPathResource("seed/modules.json").getInputStream()) {
            String json = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            List<Module> modules = objectMapper.readValue(json, new TypeReference<List<Module>>() {});
            moduleRepository.saveAll(modules);
            System.out.println(modules);
        } catch (IOException e) {
            throw e;
        } finally {
            System.out.println("Modules seeded");
        }
    }
}
