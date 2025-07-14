global:
  scrape_interval: 15s
  evaluation_interval: 15s

alerting:
  alertmanagers:
    - static_configs:
        - targets: []  # aquí tu Alertmanager si lo usas

rule_files:
  # - "first_rules.yml"
  # - "second_rules.yml"

scrape_configs:
  - job_name: "authors-app"
    scrape_interval: 5s
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["localhost:8080"]

  - job_name: "books-app"
    scrape_interval: 5s
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["localhost:9091"]


  - job_name: "prometheus"
    scrape_interval: 15s
    static_configs:
      - targets: ["localhost:9090"]
        labels:
          app: "prometheus"
