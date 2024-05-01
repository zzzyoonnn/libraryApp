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


## 문제 3
