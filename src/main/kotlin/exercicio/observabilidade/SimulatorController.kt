package exercicio.observabilidade

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class SimulatorController {

    @GetMapping("/cpu-high")
    fun cpuHigh(): ResponseEntity<String> {
        Utils.highCpuLoadSimulation()
        return ResponseEntity.ok("CPU high load simulation completed")
    }

    @GetMapping("/memory-high")
    fun memoryHigh(): ResponseEntity<String> {
        Utils.highMemoryUsageSimulation()
        return ResponseEntity.ok("Memory high usage simulation completed")
    }

    @GetMapping("/slow-response")
    fun slowResponse(
        @RequestParam("delay", defaultValue = "5000") delay: Long
    ): ResponseEntity<String> {
        Utils.slowResponseSimulation(delay)
        return ResponseEntity.ok("Slow response simulation completed")
    }

    @GetMapping("/fast-calls")
    fun fastCalls(
        @RequestParam("times", defaultValue = "10") times: Int
    ): ResponseEntity<String> {
        Utils.fastCallSimulation(times)
        return ResponseEntity.ok("Fast calls simulation completed")
    }
}