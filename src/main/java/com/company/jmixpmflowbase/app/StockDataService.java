package com.company.jmixpmflowbase.app;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.jmix.chartsflowui.data.item.MapDataItem;
import io.jmix.core.Resources;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class StockDataService {

    private final Resources resources;

    public StockDataService(Resources resources) {
        this.resources = resources;
    }

    public List<MapDataItem> fetchData() {
        Resource resource = resources.getResource("com/company/jmixpmflowbase/data/TXN.csv");

        if (resource.exists()) {
            try {
                InputStream inputStream = resource.getInputStream();

                CsvMapper csvMapper = new CsvMapper();
                CsvSchema csvSchema = CsvSchema.emptySchema()
                        .withHeader()
                        .withColumnSeparator(',');

                MappingIterator<Map<String, Object>> mappingIterator = csvMapper.readerFor(Map.class)
                        .with(csvSchema)
                        .readValues(inputStream);

                return mappingIterator.readAll()
                        .stream()
                        .sorted(this::dateComparator)
                        .map(MapDataItem::new)
                        .toList();
            } catch (IOException e) {
                throw new RuntimeException("Can't read data: " + e.getMessage(), e);
            }
        }

        return Collections.emptyList();
    }

    private int dateComparator(Map<String, Object> item1, Map<String, Object> item2) {
        String date1 = (String) item1.get("Date");
        String date2 = (String) item2.get("Date");
        return date1.compareTo(date2);
    }
}