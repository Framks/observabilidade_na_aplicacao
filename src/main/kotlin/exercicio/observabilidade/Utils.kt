package exercicio.observabilidade

import kotlin.math.sqrt
import kotlin.random.Random

object Utils {

    fun highCpuLoadSimulation(iterations: Int = 10_000_000) {
        println("Iniciando simulação de uso de CPU...")
        var result = 0.0
        for (i in 1..iterations) {
            result += sqrt(i.toDouble()) * Random.nextDouble()
        }
        println("Cálculo finalizado. Resultado: $result")
    }

    fun highMemoryUsageSimulation(size: Int = 50_000_000) {
        println("Iniciando simulação de uso de memória...")
        val bigList = MutableList(size) { Random.nextInt() }
        println("Lista criada com ${bigList.size} elementos. Uso de memória elevado.")
        // Mantém a lista viva por um tempo
        Thread.sleep(5000)
    }

    fun slowResponseSimulation(delayMillis: Long = 5000) {
        println("Simulando operação lenta...")
        Thread.sleep(delayMillis)
        println("Resposta enviada após $delayMillis ms.")
    }

    fun fastCallSimulation(times: Int = 10) {
        println("Simulando chamadas rápidas...")
        repeat(times) {
            println("Chamada rápida ${it + 1} concluída em ${Random.nextInt(1, 50)} ms.")
        }
    }
}