package br.com.uepg.pagamentosmensalistastb.erros;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ResponseError> trataErroDeEntidadeNaoEncontrada(EntityNotFoundException ex) {
        var erro = GetErrorName.getName(ex.getClass().getSimpleName());
        var message = ex.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(erro, message));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseError> trataErroDeParseNoJson(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof MismatchedInputException mismatchedInputException) {
            var fieldName = mismatchedInputException.getPath().getFirst().getFieldName();
            var expectedType = mismatchedInputException.getTargetType().getSimpleName();
            var errorMessage = "Erro ao processar a solicitação. O campo '" + fieldName +
                    "' deve ser do tipo " + expectedType + ".";

            return ResponseEntity.badRequest().body(new ResponseError(GetErrorName.getName(ex.getClass().getSimpleName()), errorMessage));
        }

        var erro = GetErrorName.getName(ex.getClass().getSimpleName());
        var message = ex.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(erro, message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> trataErroDeValidacao(MethodArgumentNotValidException ex) {
        var erro = GetErrorName.getName(ex.getClass().getSimpleName());
        var erros = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        var message = "Os seguintes campos apresentaram erro:\n" + String.join("\n", erros) + ".";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(erro, message));
    }

    @ExceptionHandler({NoResourceFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<ResponseError> trataErroDeRotaNaoEncontrada(NoResourceFoundException ex) {
        var erro = GetErrorName.getName(ex.getClass().getSimpleName());
        var message = "A rota solicitada não foi encontrada";

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(erro, message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseError> trateErroDeUrlOuValorInvalidoDeCampo(MethodArgumentTypeMismatchException ex) {
        var erro = GetErrorName.getName(ex.getClass().getSimpleName());
        var message = ex.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(erro, message));
    }
}
