package sistema_de_gestao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_gestao.services.HistoricoService;

@RestController
@RequestMapping("/api/v1/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(historicoService.listarTodos());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<?> filtrar(
            @RequestParam(required = false) String entidade,
            @RequestParam(required = false) String operacao,
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        return ResponseEntity.ok(
                historicoService.filtrar(entidade, operacao, inicio, fim)
        );
    }
}