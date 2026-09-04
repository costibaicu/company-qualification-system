package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<Company> loadCompanies(String filePath)
    {
        List<Company> listaCompanii = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                if (!line.trim().isEmpty())
                {
                    Company company = mapper.readValue(line, Company.class);
                    listaCompanii.add(company);
                }
            }
        } catch (IOException e)
        {
            throw new RuntimeException("Error reading company file: " + e.getMessage(), e);
        }

        return listaCompanii;
    }
}