# saga pattern
Spring boot saga pattern demo

## 建立環境
```shell
docker compose -f ./docker/docker-compose.yml -p saga-pattern-demo up -d
```

## 專案說明
| 專案            | 說明                                                  |
|---------------|-----------------------------------------------------|
| web-app       | 使用者統一的入口 API (角色為 Saga Pattern 中的編排器(Orchestrator)) |
| inventory-api | 庫存 API                                              |
| payment-api   | 付款 API                                              |
| openapi       | 定義 API 的規格 (提供其他服務做為元件使用)                           |

## 服務網址
| 服務            | 網址                    | 備註                 |
|---------------|-----------------------|--------------------|
| web-app       | http://localhost:8080 |                    |
| inventory-api | http://localhost:8081 |                    |
| payment-api   | http://localhost:8082 |                    |
| zipkin        | http://localhost:9411 | 使用 zipkin 記錄服務間的連線 |

## 測試案例
### 成功流程
- 指令
    ```shell
    curl --location 'http://localhost:8080/api/order/add' \
    --header 'Content-Type: application/json' \
    --data '{
        "inventoryCount": 1,
        "amount": 1
    }'
    ```
- 流程圖
    ```plantuml
    @startuml
    actor "user"
    participant "web-bff\n(orchestration)" as web_bff
    participant "inventory-api" as inventory_api
    participant "payment-api" as payment_api
    
    user -> web_bff: Start Transaction
    web_bff -> inventory_api: Execute Step 1
    web_bff <-- inventory_api: Success
    web_bff -> payment_api: Execute Step 2
    web_bff <-- payment_api: Success
    user <-- web_bff: Transaction Complete
    @enduml
    ```

### 失敗流程
- 指令
    ```shell
    curl --location 'http://localhost:8080/api/order/add' \
    --header 'Content-Type: application/json' \
    --data '{
        "inventoryCount": 1,
        "amount": null
    }'
    ```
- 流程圖
    ```plantuml
    @startuml
    actor "user"
    participant "web-bff\n(orchestration)" as web_bff
    participant "inventory-api" as inventory_api
    participant "payment-api" as payment_api
    
    user -> web_bff: Start Transaction
    web_bff -> inventory_api: Execute Step 1
    web_bff <-- inventory_api: Success
    web_bff -> payment_api: Execute Step 2
    web_bff <-- payment_api: Failure
    web_bff -> inventory_api: Rollback Step 1
    web_bff <-- inventory_api: Success
    user <-- web_bff: Transaction Complete
    @enduml
    ```
