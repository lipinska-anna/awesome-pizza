# awesome-pizza
## Backend Setup
Clone the repository:

``` bash
git clone https://github.com/lipinska-anna/awesome-pizza.git
cd awesome-pizza
```
Configure your database connection in src/main/resources/application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/awesome_pizza
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```
Run the backend:
```bash
./mvnw spring-boot:run
```
The backend will be available at http://localhost:8080.

## API Endpoints

### Order API

- **POST** `/api/order/create`: Create a new order
- **PUT** `/api/order/update`: Update an existing order
- **PUT** `/api/order/update/{id}/{status}`: Update the status of an order
- **DELETE** `/api/order/delete/{id}`: Delete an order
- **GET** `/api/order/{id}`: Get details of a specific order
- **GET** `/api/order/queue`: Get a list of all orders

### Order Status Enum

The following statuses are supported:

- **NEW**
- **IN_PROGRESS**
- **READY**
