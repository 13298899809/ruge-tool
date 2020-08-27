package com.ruge.core;

import lombok.SneakyThrows;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class ElasticToolTest {
    ElasticTool elasticTool = new ElasticTool();

    @Before
    public void configure() {
        elasticTool.configure();
    }

    @Test
    public void createIndex() {
        try {
            elasticTool.createIndex("demo1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createIndex2() {
        try {
            elasticTool.createIndex("demo12", "demo2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Test
    public void deleteIndex() {
        elasticTool.deleteIndex("demo12");
    }

    @Test
    public void checkIndexExists() {
        elasticTool.checkIndexExists("demo1");
        elasticTool.checkIndexExists("demo2");
    }

    @SneakyThrows
    @Test
    public void openIndex() {
        elasticTool.openIndex("demo1");
    }

    @SneakyThrows
    @Test
    public void closeIndex() {
        elasticTool.closeIndex("demo1");
    }

    @Test
    public void setFieldsMapping() {
    }

    @SneakyThrows
    @Test
    public void addDocByJson1() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map map = new HashMap();
            map.put("vin", "aaabbbccc");
            map.put("scope", i);
            list.add(map);
            elasticTool.addDocByJson("demo1", "demo1", UUID.randomUUID().toString(), JsonTool.getBeanToJson(map));
        }
    }

    @SneakyThrows
    @Test
    public void addDocByJson2() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map map = new HashMap();
            map.put("vin", "dddfffggg");
            map.put("scope", i);
            list.add(map);
            elasticTool.addDocByJson("demo1", "demo1", UUID.randomUUID().toString(), map);
        }
    }

    @SneakyThrows
    @Test
    public void getDocument() {
        Map<String, Object> document = elasticTool.getDocument("demo1", "demo1", "12345");
        System.out.println(document);
    }

    @Test
    public void deleteDocument() {
    }

    @Test
    public void updateDocByScript() {
    }

    @Test
    public void updateDocByJson() {
    }

    @Test
    public void bulkAdd() {
    }

    @Test
    public void bulkUpdate() {
    }

    @Test
    public void bulkDelete() {
    }

    @Test
    public void multiGet() {
    }

    @Test
    public void queryByConditions() {
    }

    @Test
    public void queryAllByConditions() {
    }

    @SneakyThrows
    @Test
    public void queryBySearchRequest1() {
        AbstractAggregationBuilder trip = AggregationBuilders.terms("group_by_vin").field("vin");
        trip.subAggregation(AggregationBuilders.avg("avgScope").field("scope"));
        System.out.println(trip);
        SearchResponse searchResponse = elasticTool.queryBySearchRequest("demo1", "demo1", trip);
       // System.out.println(searchResponse);
    }
    @SneakyThrows
    @Test
    public void queryBySearchRequest2() {
        BoolQueryBuilder vin = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("vin", "aaabbbccc"));
        SearchResponse searchResponse = elasticTool.queryBySearchRequest("demo1", "demo1", vin);
        // System.out.println(searchResponse);
    }
    @SneakyThrows
    @Test
    public void queryBySearchRequest3() {

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("group_by_vin")
                .field("vin.keyword");
        aggregation.subAggregation(AggregationBuilders.avg("avgScope")
                .field("scope"));
        elasticTool.queryBySearchRequest("demo1", "demo1", aggregation);

    }
    @SneakyThrows
    @Test
    public void queryByDsl() {
        String dsl = "{\n" +
                "    \"query\":{\n" +
                "        \"bool\":{\n" +
                "            \"must\":{\n" +
                "                \"term\":{\n" +
                "                    \"vin.keyword\":\"aaabbbccc\"\n" +
                "                }}  \n" +
                "        }\n" +
                "    }\n" +
                "}";
        elasticTool.queryByDsl("demo1", "demo1", dsl);
    }
}