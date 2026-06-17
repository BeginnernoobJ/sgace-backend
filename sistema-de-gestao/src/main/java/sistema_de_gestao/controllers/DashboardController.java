package sistema_de_gestao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_de_gestao.services.DashboardService;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/metricas")
    public ResponseEntity<?> getMetricas() {
        return ResponseEntity.ok(dashboardService.getMetricas());
    }

    @GetMapping("/movimentos-recentes")
    public ResponseEntity<?> getMovimentosRecentes() {
        return ResponseEntity.ok(dashboardService.getMovimentosRecentes());
    }

    @GetMapping("/alertas-activos")
    public ResponseEntity<?> getAlertasActivos() {
        return ResponseEntity.ok(dashboardService.getAlertasActivos());
    }

    @GetMapping("/movimentos-semana")
    public ResponseEntity<?> getMovimentosSemana() {
        return ResponseEntity.ok(dashboardService.getMovimentosPorDia());
    }

    @GetMapping("/componentes-categoria")
    public ResponseEntity<?> getComponentesPorCategoria() {
        return ResponseEntity.ok(dashboardService.getComponentesPorCategoria());
    }
}