package com.ezamora.coffeeshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@SpringBootApplication
public class CoffeeShopMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopMainApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                // Verificamos si la tabla 'orders' ya tiene datos para evitar ejecutar el script múltiples veces.
                Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM orders", Integer.class);
                if (count != null && count > 0) {
                    System.out.println("La base de datos ya contiene datos. Se omite la ejecución del script de inicialización.");
                    return;
                }
            } catch (Exception e) {
                // Si la tabla no existe, se producirá una excepción, lo cual es esperado la primera vez.
                // Procedemos a ejecutar el script.
                System.out.println("Tablas no encontradas o vacías. Ejecutando script de inicialización...");
            }

            try {
                Resource resource = new ClassPathResource("db/coffee_shop.sql");
                // Usamos ScriptUtils para ejecutar el script SQL, es más robusto que procesarlo manualmente.
                ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
                System.out.println("Script de inicialización de base de datos ejecutado con éxito.");
            } catch (Exception e) {
                System.err.println("Error al ejecutar el script SQL de inicialización: " + e.getMessage());
            }
        };
    }
}
