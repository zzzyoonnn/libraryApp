package com.group.libraryapp.repository.task;

import com.group.libraryapp.dto.task.request.FruitAddRequest;
import com.group.libraryapp.dto.task.request.FruitSellRequest;
import com.group.libraryapp.dto.task.response.FruitCostResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class FruitMySqlRepository implements FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveFruit(FruitAddRequest request) {
        String sql = "INSERT INTO fruit(name, warehousingDate, price) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
    }

    @Override
    public void sellFruit(FruitSellRequest request) {
        String sql = "UPDATE fruit SET saleStatus = 1 WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }

    @Override
    public FruitCostResponse fruitCostResponse(String name) {
        String saleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 1 GROUP BY name HAVING name = ?";
        String notSaleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 0 GROUP BY name HAVING name = ?";

        long soldCost = jdbcTemplate.queryForObject(saleSql, long.class, name);
        long unsoldCost = jdbcTemplate.queryForObject(notSaleSql, long.class, name);

        return new FruitCostResponse(soldCost, unsoldCost);
    }
}
