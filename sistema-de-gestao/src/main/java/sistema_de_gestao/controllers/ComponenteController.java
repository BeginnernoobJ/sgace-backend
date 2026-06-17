package sistema_de_gestao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sistema_de_gestao.dtos.ComponenteRequestDTO;
import sistema_de_gestao.services.ComponenteService;

@RestController
@RequestMapping("/api/v1/componentes")
public class ComponenteController {

    @Autowired
    private ComponenteService componenteService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(componenteService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscar(@PathVariable String codigo) {
        return ResponseEntity.ok(componenteService.buscarPorCodigo(codigo));
    }

    @GetMapping("/search")
    public ResponseEntity<?> pesquisar(@RequestParam String descricao) {
        return ResponseEntity.ok(componenteService.pesquisar(descricao));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ComponenteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(componenteService.criar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo,
                                         @RequestBody ComponenteRequestDTO dto) {
        return ResponseEntity.ok(componenteService.actualizar(codigo, dto));
    }

    @PatchMapping("/{codigo}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable String codigo) {
        componenteService.desactivar(codigo);
        return ResponseEntity.noContent().build();
    }
}
