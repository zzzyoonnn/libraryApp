package com.group.libraryapp.repository.task;

import com.group.libraryapp.dto.task.request.FruitAddRequest;
import com.group.libraryapp.dto.task.request.FruitSellRequest;
import org.springframework.jdbc.core.JdbcTemplate;

public class FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveFruit(FruitAddRequest request) {
        String sql = "INSERT INTO fruit(name, warehousingDate, price) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
    }

    public void sellFruit(FruitSellRequest request) {
        String sql = "UPDATE fruit SET saleStatus = 1 WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }

    public long soldFruitCostResponse(String name) {
        String saleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 1 GROUP BY name HAVING name = ?";

        return jdbcTemplate.queryForObject(saleSql, long.class, name);
    }

    public long unsoldFruitCostResponse(String name) {
        String notSaleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 0 GROUP BY name HAVING name = ?";

        return jdbcTemplate.queryForObject(notSaleSql, long.class, name);
    }
}
