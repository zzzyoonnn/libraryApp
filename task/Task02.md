# 두 번째 과제
## 문제 1
TaskController

    @GetMapping("/api/v1/calc")
        public CalculationResponse calculateNumbers(CalculationRequest request) {
            return new CalculationResponse(request);
    }

CalculationRequest

    public class CalculationRequest {
        private final int num1;
        private final int num2;

        public CalculationRequest(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        public int getNum1() {
            return num1;
        }

        public int getNum2() {
            return num2;
        }
    }

CalculationResponse

    public class CalculationResponse {
        private int plus;
        private int minus;
        private int multiply;

        public CalculationResponse(CalculationRequest request) {
            this.plus = request.getNum1() + request.getNum2();
            this.minus = request.getNum1() - request.getNum2();
            this.multiply = request.getNum1() * request.getNum2();
        }

        public int getPlus() {
            return plus;
        }

        public int getMinus() {
            return minus;
        }

        public int getMultiply() {
            return multiply;
        }
    }



## 문제 2
TaskController

    @GetMapping("api/v1/day-of-the-week")
        public DayOfTheWeekRequest getDayOfTheWeek(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return new DayOfTheWeekRequest(localDate);
    }

DayOfTheWeekRequest

    public class DayOfTheWeekRequest {
        private  String dayOfTheWeek;

        public String getDayOfTheWeek() {
            return dayOfTheWeek;
        }

        public DayOfTheWeekRequest(LocalDate date) {
            String day = date.getDayOfWeek().toString();
            this.dayOfTheWeek = day.substring(0, day.length() - 3);
        }
    }

## 문제 3
TaskController

    @PostMapping("/api/v1/sumList")
        public int sumList(@RequestBody CalculatorListRequest request) {
            List<Integer> numbers = request.getList();
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            return sum;
        }
    }


CalculatorListRequest 

    CalculatorListRequest {
        private List<Integer> numbers;

        public CalculatorListRequest(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public List<Integer> getList() {
            return numbers;
        }

        public void setList(List<Integer> list) {
            this.numbers = list;
        }
    }