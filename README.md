# Observabilidade na Aplicação

Este projeto demonstra como aplicar observabilidade em uma aplicação Kotlin/Spring Boot utilizando OpenTelemetry, Micrometer, Prometheus e Grafana.

## Requisitos

- Java 17+
- Docker e Docker Compose
- Gradle (wrapper incluído)

## Instalação e Execução

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Framks/observabilidade_na_aplicacao.git
   cd observabilidade_na_aplicacao
   ```

2. **Execute a aplicação:**
   ```bash
   ./gradlew bootRun
   ```
   A aplicação estará disponível em `http://localhost:8080`.

## Observabilidade: Prometheus e Grafana

1. **Suba os serviços de monitoramento:**
   ```bash
   docker-compose up -d
   ```
   - Prometheus: http://localhost:9090
   - Grafana: http://localhost:3000 (usuário: admin, senha: admin)

2. **Configuração do Prometheus:**
   O Prometheus já está configurado para coletar métricas da aplicação via o endpoint `/actuator/prometheus`.

3. **Acesse o Grafana:**
   - Faça login com as credenciais acima.
   - Adicione o Prometheus como fonte de dados (`http://prometheus:9090`).
   - Importe dashboards prontos ou crie seus próprios para visualizar métricas.

## Testes

Para rodar os testes automatizados:
```bash
./gradlew test
```

## Endpoints Úteis

- `/actuator/prometheus` — Exibe métricas para Prometheus

## Banco de Dados

O projeto utiliza H2 em memória, sem necessidade de configuração adicional para desenvolvimento.

## Dicas e Troubleshooting

- Certifique-se de que as portas 8080, 9090 e 3000 estão livres.
- Se algum serviço não subir, verifique logs com `docker-compose logs <serviço>`.
- Para reiniciar tudo: `docker-compose down && docker-compose up -d`

## Referências
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Micrometer](https://micrometer.io/)
- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)

---

Este projeto é para fins de estudo e demonstração de observabilidade em aplicações modernas.

