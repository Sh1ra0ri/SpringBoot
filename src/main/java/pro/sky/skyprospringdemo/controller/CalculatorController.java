package pro.sky.skyprospringdemo.controller;

import pro.sky.skyprospringdemo.model.ErrorResponse;
import pro.sky.skyprospringdemo.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private static final String MISSING_PARAMS_MESSAGE = "Параметры num1 и num2 обязательны";
    private static final String DIVISION_BY_ZERO_MESSAGE = "Деление на ноль недопустимо";
    private static final String WELCOME_MESSAGE = "Добро пожаловать в калькулятор";
    private static final String RESULT_FORMAT = "%d %s %d = %s";

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    @GetMapping("/plus")
    public ResponseEntity<?> addNumbers(@RequestParam(name = "num1", required = false) Integer num1,
                                      @RequestParam(name = "num2", required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(MISSING_PARAMS_MESSAGE, HttpStatus.BAD_REQUEST.value()));
        }
        int result = calculatorService.add(num1, num2);
        return ResponseEntity.ok(String.format(RESULT_FORMAT, num1, "+", num2, result));
    }

    @GetMapping("/minus")
    public ResponseEntity<?> subtractNumbers(@RequestParam(name = "num1", required = false) Integer num1,
                                           @RequestParam(name = "num2", required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(MISSING_PARAMS_MESSAGE, HttpStatus.BAD_REQUEST.value()));
        }
        int result = calculatorService.subtract(num1, num2);
        return ResponseEntity.ok(String.format(RESULT_FORMAT, num1, "−", num2, result));
    }

    @GetMapping("/multiply")
    public ResponseEntity<?> multiplyNumbers(@RequestParam(name = "num1", required = false) Integer num1,
                                           @RequestParam(name = "num2", required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(MISSING_PARAMS_MESSAGE, HttpStatus.BAD_REQUEST.value()));
        }
        int result = calculatorService.multiply(num1, num2);
        return ResponseEntity.ok(String.format(RESULT_FORMAT, num1, "*", num2, result));
    }

    @GetMapping("/divide")
    public ResponseEntity<?> divideNumbers(@RequestParam(name = "num1", required = false) Integer num1,
                                         @RequestParam(name = "num2", required = false) Integer num2) {
        if (num1 == null || num2 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(MISSING_PARAMS_MESSAGE, HttpStatus.BAD_REQUEST.value()));
        }
        if (num2 == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(DIVISION_BY_ZERO_MESSAGE, HttpStatus.BAD_REQUEST.value()));
        }
        double result = calculatorService.divide(num1, num2);
        return ResponseEntity.ok(String.format(RESULT_FORMAT, num1, "/", num2, String.format("%.0f", result)));
    }
}