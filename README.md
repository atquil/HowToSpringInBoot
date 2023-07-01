# HowToSpringBoot: Monitoring and Alerting

## How it all works: 

**_Actuator provides endpoints_** for monitoring and managing your application like **health, metrics, and [more](https://docs.spring.io/spring-boot/docs/2.5.6/reference/html/actuator.html#actuator.endpoints)**. 
On top of it, it  provide **micrometer**  facility to **collect metrics**, which will be used by **Prometheus** to _scrape these metrics_ 
from your application and **store them in its time-series database**. 
Finally, **Grafana** can be **used to visualize these metrics in dashboards** .


## Documentation: 
1. Actuator: https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html
2. Prometheus:
3. Grafana: 


## Actuator Setup
1. Add dependency to your application

   ### Gradle
   ```gradle
   implementation 'org.springframework.boot:spring-boot-starter-actuator'
   ```
    
   ### Maven
   ```xml
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

 2. Now enable the Actuator

    ### application.yml
    ```yml
    server:
      port: 8080
    management:
      endpoints:
        web:
          exposure:
            include: "*" 
      endpoint:
        health:
          show-details: always
    
    ```
    
    ### application.properties
    ```properties
    server.port = 8080
    
    management.endpoints.web.exposure.include=*
    management.endpoint.health.show-details:always
    ```
 3. After running the application test the endpoint to see if actuator is setup correctly or not

    ```http request
    http://localhost:8080/actuator
    ```
    
## Prometheus 

    It provides different health measurment metrices. 

 1. Add the required dependency
    ### Gradle
    ```gradle
    implementation 'io.micrometer:micrometer-registry-prometheus:1.10.5'
    ```
    
    ### Maven
    ```xml
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <version>1.10.5</version>
    </dependency>
    ```
 2. After loding the dependency check this prometheus endpoint, to get the metrics 
    ```http request
    http://localhost:8080/actuator/prometheus
    ```
 3. Install Prometheus
    
    ## Mac User using terminal [Easiest way]
    
       - Install brew [[Youtube Tutorial](https://www.youtube.com/watch?v=S9mjz4P8_ZQ&ab_channel=atquil)]: [Brew Page](https://brew.sh/)
         - Command in terminal : ```/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"```
       - Install Promethus server -
         - Command in terminal : ```brew install prometheus```
       - Go to file location for prometheus.yml : 
         - Command in terminal 
           - location : ```cd /opt/homebrew/etc/```
           - open the file: ```vi prometheus.yml```
             - edit the file: ```Press i to insert```
             - Add **new job** 
         ```yaml
            - job_name: 'spring-actuator'
              metrics_path: '/actuator/prometheus' # It tells from where we will get the data
              scrape_interval: 5s  # Scrap data every 5 sec from path. Default is 1 min
              static_configs:
              - targets: ['localhost:8080'] # Your application host name
         ```
         -  Save the file: Press ```ESC``` then type ```:wq``` to save and quite
         - Server
           - Start the server : ```brew services start prometheus```
           - Stop the server  : ```brew services stop prometheus```
     
    ## Windows User

    - Install from official link: [Download Link](https://prometheus.io/download/)
    - Extract the zip file of the downloded folder
         - Open ```prometheus.yml``` file
           - Add **new job command**

             ```yaml
                   - job_name: 'spring-actuator'
                     metrics_path: '/actuator/prometheus' # It tells from where we will get the data
                     scrape_interval: 5s  # Scrap data every 5 sec from path. Default is 1 min
                     static_configs:
                     - targets: ['localhost:8080'] # Your application host name
                ```
     - Go to prometheius location: 
       - Run the server from terminal: ```.\prometheus.exe```
       - Stop the server: ``` /etc/init.d/prometheus stop```
       ##
4. Open the Prometheus dashboad : ```http://localhost:9090/```
5. Select different health Matrics to see the data ![](../../../../../var/folders/w6/g7d6485x24qgbzyxrb370m800000gn/T/TemporaryItems/NSIRD_screencaptureui_PgkYFS/Screenshot 2023-07-01 at 11.20.10 PM.png)

# Grafana: [Download Grafana](https://grafana.com/grafana/download`) 

1. Installation 
## Mac : [Installation Guide](https://grafana.com/docs/grafana/latest/setup-grafana/installation/mac/)

 - In brew type: ```brew install grafana``` 
 - Server
     - Start the server : ```brew services start grafana```
     - Stop the server  : ```brew services stop grafana```

## Windows
 - Go to download file
 - Extract the zip file
 - Go inside the ```bin``` folder
 - Open the terminal 
 - Server
     - Run the server from terminal: ```.\grafana-server.exe```
     - Stop the server: ``` /etc/init.d/grafana stop```

2. Opening the Grafana
   - Go to localhost: ```http://localhost:3000```
   - Default login details:
     - Username: admin
     - Password: admin
3. Skip the new password part (for now)
4. Setup the **Data source**:
   - [Older] Go to configuration --> Datasource --> Add Data Source --> Select Prometheus 
   - [New] Go to connection --> Datasource --> Add Data source --> Select Prometheus
5. Setup for prometheus : 
   - Add prometheus server url: ```http://localhost:9090/```
   - Click on ```Save and test```
7. Creating a dashboard: 
   - Click on Dashboard --> Add a panel 
   - Inside the pannel configuration --> Go to Metrics --> Write any of the metrics that you have from prometheus or select from dropdown
     - E.g. ```disk_total_bytes```
   - Click on ```Save``` then ```Apply```

## Custom Grafana Dashboard

   - You can also import a Spring boot dashboard using json : [Grafana-Spring-Boot-Dashboard-Options](https://grafana.com/grafana/dashboards/?search=springboot&dataSource=prometheus&orderBy=reviewsCount&direction=desc/)
   - Download the SpringBoot APM Dashboard ( Or any other) e.g. [Prometheus dashboard](https://grafana.com/grafana/dashboards/3662-prometheus-2-0-overview/)
   - Import Dashboard: On top right corner there is a ```+``` button click there --> Import dashboard
   - Put the name for the dashboard
   - Put the datasource as : Prometheus 
   - Save the dashboard

