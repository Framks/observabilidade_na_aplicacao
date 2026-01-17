package exercicio.observabilidade

import kotlin.math.sqrt
import kotlin.random.Random
import org.slf4j.LoggerFactory

object Utils {

    val logger = LoggerFactory.getLogger(Utils::class.java)

    fun highCpuLoadSimulation(iterations: Int = 10_000_000) {
        Utils.logger.info("Iniciando simulação de uso de CPU...")
        var result = 0.0
        for (i in 1..iterations) {
            result += sqrt(i.toDouble()) * Random.nextDouble()
        }
        Utils.logger.info("Cálculo finalizado. Resultado: $result")
    }

    fun highMemoryUsageSimulation(size: Int = 50_000_000) {
        Utils.logger.info("Iniciando simulação de uso de memória...")
        val bigList = MutableList(size) { Random.nextInt() }
        Utils.logger.info("Lista criada com ${bigList.size} elementos. Uso de memória elevado.")
        // Mantém a lista viva por um tempo
        Thread.sleep(5000)
    }

    fun slowResponseSimulation(delayMillis: Long = 5000) {
        Utils.logger.info("Simulando operação lenta...")
        Thread.sleep(delayMillis)
        Utils.logger.info("Resposta enviada após $delayMillis ms.")
    }

    fun fastCallSimulation(times: Int = 10) {
        Utils.logger.info("Simulando chamadas rápidas...")
        repeat(times) {
            Utils.logger.info("Chamada rápida ${it + 1} concluída em ${Random.nextInt(1, 50)} ms.")
        }
    }
}