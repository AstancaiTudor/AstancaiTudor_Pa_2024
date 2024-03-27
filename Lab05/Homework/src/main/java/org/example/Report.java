package org.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Report implements Command {
    private Repository repository;

    public Report(Repository repository) {
        this.repository = repository;
    }

    public void execute() {
        try {
            // Inițializare Velocity
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            velocityEngine.init();

            File directory = new File(repository.getDirectory());
            StringBuilder result = new StringBuilder();

            for (Person p : repository.getDocuments().keySet()) {
                result.append(p.name()).append(" ").append(p.id()).append(":<br>");
                for (Document d : repository.getDocuments().get(p)) {
                    result.append(d.title()).append(".").append(d.extension()).append("<br>");
                }
                result.append("<br>");
            }


            // Creare context Velocity
            VelocityContext context = new VelocityContext();
            context.put("files", result.toString());
            context.put("directoryName", directory.getName());

            // Încărcare șablon
            Template template = velocityEngine.getTemplate("reportTemplate.vm");

            // Generare raport
            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            // Salvare raport în fișier HTML
            String reportPath = "raport.html";
            try (FileWriter fileWriter = new FileWriter(reportPath)) {
                fileWriter.write(writer.toString());
            }

            // Deschidere raport în browser
            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                Desktop.getDesktop().open(reportFile);
            } else {
                System.err.println("Fișierul raport nu a putut fi creat.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
