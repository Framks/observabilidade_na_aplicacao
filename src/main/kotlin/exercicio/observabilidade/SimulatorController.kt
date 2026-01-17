package exercicio.observabilidade

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class SimulatorController {

    val logger = LoggerFactory.getLogger(SimulatorController::class.java)

    @GetMapping("/cpu-high")
    fun cpuHigh(): ResponseEntity<String> {
        logger.info("Starting CPU high load simulation")
        Utils.highCpuLoadSimulation()
        return ResponseEntity.ok("CPU high load simulation completed")
    }

    @GetMapping("/memory-high")
    fun memoryHigh(): ResponseEntity<String> {
        logger.info("Starting memory high usage simulation")
        Utils.highMemoryUsageSimulation()
        return ResponseEntity.ok("Memory high usage simulation completed")
    }

    @GetMapping("/slow-response")
    fun slowResponse(
        @RequestParam("delay", defaultValue = "5000") delay: Long
    ): ResponseEntity<String> {
        logger.info("Starting slow response simulation with delay: $delay ms")
        Utils.slowResponseSimulation(delay)
        return ResponseEntity.ok("Slow response simulation completed")
    }

    @GetMapping("/fast-calls")
    fun fastCalls(
        @RequestParam("times", defaultValue = "10") times: Int
    ): ResponseEntity<String> {
        logger.info("Starting fast calls simulation with times: $times")
        Utils.fastCallSimulation(times)
        return ResponseEntity.ok("Fast calls simulation completed")
    }
}