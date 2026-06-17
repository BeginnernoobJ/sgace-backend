package sistema_de_gestao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_gestao.entities.Fornecedor;
import sistema_de_gestao.services.FornecedorService;

@RestController
@RequestMapping("/api/v1/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscar(@PathVariable String codigo) {
        return ResponseEntity.ok(fornecedorService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fornecedorService.criar(fornecedor));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo,
                                        @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.ok(fornecedorService.actualizar(codigo, fornecedor));
    }

    @PatchMapping("/{codigo}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable String codigo) {
        fornecedorService.desactivar(codigo);
        return ResponseEntity.noContent().build();
    }
}