package sistema_de_gestao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_gestao.services.MovimentoStockService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/movimentos")
public class MovimentoStockController {

    @Autowired
    private MovimentoStockService movimentoService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(movimentoService.listarTodos());
    }

    @GetMapping("/componente/{codigo}")
    public ResponseEntity<?> listarPorComponente(@PathVariable String codigo) {
        return ResponseEntity.ok(movimentoService.listarPorComponente(codigo));
    }

    @PostMapping
    public ResponseEntity<?> registar(@RequestBody Map<String, Object> body) {
        try {
            String codigoStock  = (String) body.get("codigoStock");
            String tipo         = (String) body.get("tipo");
            Integer quantidade  = (Integer) body.get("quantidade");
            String observacao   = (String) body.get("observacao");
            Integer utilizadorId = (Integer) body.get("utilizadorId");

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(movimentoService.registar(
                            codigoStock, tipo, quantidade, observacao, utilizadorId
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        }
    }
}