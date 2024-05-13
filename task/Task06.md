# 여섯 번째 과제


## 문제 1
### 과제 #4에서 만들었던 API를 강의 내용처럼 Controller - Service - Repository로 분리해보세요!

기존 API

    package com.group.libraryapp.controller.task;

    @RestController
    public class TaskController {

        private final JdbcTemplate jdbcTemplate;

        public TaskController(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @GetMapping("/api/v1/calc")
        public CalculationResponse calculateNumbers(CalculationRequest request) {
            return new CalculationResponse(request);
        }

        @GetMapping("/api/v1/day-of-the-week")
        public DayOfTheWeekRequest getDayOfTheWeek(@RequestParam String date) {
            LocalDate localDate = LocalDate.parse(date);
            return new DayOfTheWeekRequest(localDate);
        }

        @PostMapping("/api/v1/add-numbers")
        public int addNumbers(@RequestBody CalculatorListRequest request) {
            List<Integer> numbers = request.getNumbers();
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            return sum;
        }

        @PostMapping("/api/v1/fruit")
        public  void saveFruit(@RequestBody FruitAddRequest request) {
            String sql = "INSERT INTO fruit(name, warehousingDate, price) VALUES (?, ?, ?)";

            jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
        }

        @PutMapping("/api/v1/fruit")
        public void sellFruit(@RequestBody FruitSellRequest request) {
            String sql = "UPDATE fruit SET saleStatus = 1 WHERE id = ?";
            jdbcTemplate.update(sql, request.getId());
        }

        @GetMapping("/api/v1/fruit/stat")
        public FruitCostResponse fruitCostResponse(@RequestParam String name) {
            String saleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 1 GROUP BY name HAVING name = ?";
            String notSaleSql = "SELECT sum(price) FROM fruit WHERE saleStatus = 0 GROUP BY name HAVING name = ?";

            //  sql 쿼리를 실행하여 결과를 long 데이터 타입으로 변환하여 받기 위해 long.class 작성
            long soldCost = jdbcTemplate.queryForObject(saleSql, long.class, name);
            long unsoldCost = jdbcTemplate.queryForObject(notSaleSql, long.class, name);

            return new FruitCostResponse(soldCost, unsoldCost);
        }
    }

분리된 Controller

    package com.group.libraryapp.controller.task;

    @RestController
    public class FruitController {

        private final FruitService fruitService;

        public FruitController(JdbcTemplate jdbcTemplate) {
            this.fruitService = new FruitService(jdbcTemplate);
        }

        @PostMapping("/api/v1/fruit")
        public  void saveFruit(@RequestBody FruitAddRequest request) {
            fruitService.saveFruit(request);
        }


        @PutMapping("/api/v1/fruit")
        public void sellFruit(@RequestBody FruitSellRequest request) {
            fruitService.sellFruit(request);
        }

        @GetMapping("/api/v1/fruit/stat")
        public FruitCostResponse fruitCostResponse(@RequestParam String name) {
            return fruitService.fruitCostResponse(name);
        }
    }

분리된 Service

    package com.group.libraryapp.service.task;

    public class FruitService {

        private final FruitRepository fruitRepository;

        public FruitService(JdbcTemplate jdbcTemplate) {
            this.fruitRepository = new FruitRepository(jdbcTemplate);
        }

        public void saveFruit(FruitAddRequest request) {
            fruitRepository.saveFruit(request);
        }

        public void sellFruit(FruitSellRequest request) {
            fruitRepository.sellFruit(request);
        }

        public FruitCostResponse fruitCostResponse(String name) {
            long soldCost = fruitRepository.soldFruitCostResponse(name);
            long unsoldCost = fruitRepository.unsoldFruitCostResponse(name);

            return new FruitCostResponse(soldCost, unsoldCost);
        }
    }

분리된 Repository

    package com.group.libraryapp.repository.task;

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

## 문제 2
### 문제 1에서 코드가 분리되면 FruitController / FruitService / FruitRepository가 생겼을 것입니다.
### 기존에 작성했던 FruitRepository를 FruitMemoryRepository와 FruitMysqlRepository로 나누고 @Primary 어노테이션을 활용해 두 Repository를 바꿔가며 동작시킬 수 있도록 코드를 변경해보세요!
FruitService

    package com.group.libraryapp.service.task;

    @Service
    public class FruitService {

        private final FruitRepository fruitRepository;

        public FruitService(FruitRepository fruitRepository) {
            this.fruitRepository = fruitRepository;
        }

        public void saveFruit(FruitAddRequest request) {
            fruitRepository.saveFruit(request);
        }

        public void sellFruit(FruitSellRequest request) {
            fruitRepository.sellFruit(request);
        }

        public FruitCostResponse fruitCostResponse(String name) {
            return fruitRepository.fruitCostResponse(name);
        }
    }


FruitRepository

    package com.group.libraryapp.repository.task;

    public interface FruitRepository {
        void saveFruit(FruitAddRequest request);

        void sellFruit(FruitSellRequest request);

        FruitCostResponse fruitCostResponse(String name);
    }

FruitMySqlRepository

    package com.group.libraryapp.repository.task;

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

FruitMemoryRepository

    package com.group.libraryapp.repository.task;

    @Repository
    public class FruitMemoryRepository implements FruitRepository {

        private final JdbcTemplate jdbcTemplate;

        public FruitMemoryRepository(JdbcTemplate jdbcTemplate) {
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


