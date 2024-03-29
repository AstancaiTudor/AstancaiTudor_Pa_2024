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

/**
 * Command for generating a report of the documents in the repository.
 * @author Tudor
 */
public class Report implements Command {
    private Repository repository;

    public Report(Repository repository) {
        this.repository = repository;
    }

    /**
     * Generates a report of the documents in the repository.
     * The report is generated using the Velocity template engine.
     * The report is saved in a file named "raport.html" and is opened using the default browser.
     */
    public void execute() {
        try {
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


            VelocityContext context = new VelocityContext();
            context.put("files", result.toString());
            context.put("directoryName", directory.getName());

            Template template = velocityEngine.getTemplate("reportTemplate.vm");

            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            String reportPath = "raport.html";
            try (FileWriter fileWriter = new FileWriter(reportPath)) {
                fileWriter.write(writer.toString());
            }

            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                Desktop.getDesktop().open(reportFile);
            } else {
                System.err.println("Fisierul raport nu a putut fi creat.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
