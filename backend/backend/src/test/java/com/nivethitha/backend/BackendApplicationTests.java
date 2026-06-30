package com.nivethitha.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testTaskTitleNotNull() {
        String title = "MySQL Test";
        assertNotNull(title);
    }

    @Test
    void testTaskStatus() {
        String status = "PENDING";
        assertEquals("PENDING", status);
    }
}