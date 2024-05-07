# 네 번째 과제


## 문제 1
### fruit 테이블 생성하기

    mysql> create table fruit (
    ->   id bigint auto_increment,
    ->   name varchar(20),
    ->   warehousingDate date,
    ->   price int,
    ->   saleStatus BOOLEAN DEFAULT FALSE,
    ->   primary key(id)
    -> );
    Query OK, 0 rows affected (0.01 sec)

id와 price의 타입을 long으로, saleStatus를 boolean으로 하려 했다. 하지만, MySQL에서 long은 데이터 타입이 아니기에 id의 int는 큰 정수를 저장할 수 있는 BIGINT 데이터 타입을 사용했다.

### dto 생성하기


    package com.group.libraryapp.dto.task.request;

    public class FruitAddRequest {

        private long id;
        private String name;
        private String warehousingDate;
        private long price;
        private boolean saleStatus;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getWarehousingDate() {
            return warehousingDate;
        }

        public long getPrice() {
            return price;
        }

        public boolean isSaleStatus() {
            return saleStatus;
        }
    }

### Cotroller 생성하기


    package com.group.libraryapp.controller.task;

    @RestController
    public class TaskController {

        private final JdbcTemplate jdbcTemplate;

        public TaskController(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @PostMapping("/api/v1/fruit")
        public  void saveFruit(@RequestBody FruitAddRequest request) {
            String sql = "INSERT INTO fruit(name, warehousingDate, price) VALUES (?, ?, ?)";

            jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
        }

    }

삽입된 데이터를 확인하면 다음과 같았다.

    mysql> select * from fruit;
    +----+-----------+-----------------+-------+------------+
    | id | name      | warehousingDate | price | saleStatus |
    +----+-----------+-----------------+-------+------------+
    |  1 | 사과      | 2024-05-07      |  5000 |          0 |
    |  2 | 바나나    | 2024-05-07      |  3000 |          0 |
    |  3 | 복숭아    | 2024-05-07      |  6000 |          0 |
    |  4 | 사과      | 2024-05-07      |  8000 |          0 |
    |  5 | 사과      | 2024-05-07      |  3000 |          0 |
    +----+-----------+-----------------+-------+------------+
    5 rows in set (0.00 sec)

## 문제 2
### dto 생성하기
    package com.group.libraryapp.dto.task.request;

    public class FruitSellRequest {
        private long id;

        public long getId() {
            return id;
        }
    }

### Cotroller 생성하기
    package com.group.libraryapp.controller.task;    

    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitSellRequest request) {
        String sql = "UPDATE fruit SET saleStatus = 1 WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }

결과는 다음과 같다.

    mysql> select * from fruit;
    +----+-----------+-----------------+-------+------------+
    | id | name      | warehousingDate | price | saleStatus |
    +----+-----------+-----------------+-------+------------+
    |  1 | 사과      | 2024-05-07      |  5000 |          0 |
    |  2 | 바나나    | 2024-05-07      |  3000 |          0 |
    |  3 | 복숭아    | 2024-05-07      |  6000 |          0 |
    |  4 | 사과      | 2024-05-07      |  8000 |          0 |
    |  5 | 사과      | 2024-05-07      |  3000 |          0 |
    +----+-----------+-----------------+-------+------------+
    5 rows in set (0.00 sec)

    mysql> select * from fruit;
    +----+-----------+-----------------+-------+------------+
    | id | name      | warehousingDate | price | saleStatus |
    +----+-----------+-----------------+-------+------------+
    |  1 | 사과      | 2024-05-07      |  5000 |          0 |
    |  2 | 바나나    | 2024-05-07      |  3000 |          0 |
    |  3 | 복숭아    | 2024-05-07      |  6000 |          1 |
    |  4 | 사과      | 2024-05-07      |  8000 |          0 |
    |  5 | 사과      | 2024-05-07      |  3000 |          0 |
    +----+-----------+-----------------+-------+------------+
    5 rows in set (0.00 sec)

## 문제 3
### dto 생성하기
    package com.group.libraryapp.dto.task.response;

    public class FruitCostResponse {

        private long soldCost;
        private long unsoldCost;

        public FruitCostResponse(long soldCost, long unsoldCost) {
            this.soldCost = soldCost;
            this.unsoldCost = unsoldCost;
        }

        public long getSoldCost() {
            return soldCost;
        }

        public long getUnsoldCost() {
            return unsoldCost;
        }
    }

### Cotroller 생성하기
    package com.group.libraryapp.controller.task;  

    @GetMapping("/api/v1/fruit/stat")
    public FruitCostResponse fruitCostResponse(@RequestParam String name) {
        String saleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 1 GROUP BY name HAVING name = ?";
        String notSaleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 0 GROUP BY name HAVING name = ?";

        //  sql 쿼리를 실행하여 결과를 long 데이터 타입으로 변환하여 받기 위해 long.class 작성
        long soldCost = jdbcTemplate.queryForObject(saleSql, long.class, name);
        long unsoldCost = jdbcTemplate.queryForObject(notSaleSql, long.class, name);

        return new FruitCostResponse(soldCost, unsoldCost);
    }

결과는 다음과 같다.

    http://localhost:8080/api/v1/fruit/stat?name=사과

    {
    "soldCost": 8000,
    "unsoldCost": 8000
    }