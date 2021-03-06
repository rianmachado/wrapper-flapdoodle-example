# Demo Spring Boot with wrapper for flapdoodle
Maven plugin wrapper for the flapdoodle.de embedded MongoDB API.

Essa demo mostra como utilizar o wrapper-flapdoodle a partir do embedmongo-maven-plugin. Para complemento da demo disponibilizamos também uma Api de produto para gravação e consulta de dados armazenados no mongo em memória. Você também poderá utilizar os teste de unidade para validar a persistência e leitura dos dados.

## Versions

* Java 11
* Maven
* Lombok

## Internet bloqueada para download do binário(MongoDB)
Para start do mongo embedded sem uma conexão externa(internet) é necessário dois passos. Lembrando que a conexão externa permite fazer o download do binário do mongoDB.

* Utilize a tag `<downloadPath>` localizada dentro do POM(diretório raiz dessa demo) e informe qual URL da rede interna onde será feito download do binário do mongoDB
  
* Instale o artefato embedmongo-maven-plugin em um storage location for software packages(exemplo Nexus). 
  - Para isso tenha localmente o projeto [<< embedmongo-online-maven-plugin >>](https://github.com/rianmachado/embedmongo-online-maven-plugin).
  - Faça o ajuste no POM adequadamente apontando para seu nexus local, exemplo:
  ```xml
  	<repositories>
		<repository>
			<id>maven-group</id>
			<url>http://localhost:8081/repository/maven-group/</url>
		</repository>
	</repositories>
	<distributionManagement>
		<snapshotRepository>
			<id>nexus-snapshots</id>
			<url>http://localhost:8081/repository/maven-snapshots/</url>
		</snapshotRepository>
		<repository> 
      <id>nexus-releases</id> 
      <url>http://localhost:8081/repository/maven-releases/</url> 
			</repository>
		<repository>
			<id>nexus-central</id>
			<url>http://localhost:8081/repository/maven-central/</url>
		</repository>
	</distributionManagement>
  ```
  - Por fim rode o `mvn deploy` na raiz do projeto o qual requer JAVA 8. Se tudo ocorrer bem você terá o embedmongo-maven-plugin disponível no seu Nexus local.

## Como usar o plugin
```xml
	<plugin>
				<groupId>com.github.joelittlejohn.embedmongo</groupId>
				<artifactId>embedmongo-online-maven-plugin</artifactId>
				<version>0.5.0</version>
				<executions>
					<execution>
						<?m2e ignore?>
						<id>start</id>
						<goals>
							<goal>start</goal>
						</goals>
						<configuration>
							<port>27017</port>
							<version>2.7.1</version>
							<databaseDirectory>/tmp/mongotest</databaseDirectory>
							<logging>console</logging>
							<bindIp>127.0.0.1</bindIp>
							<downloadPath>https://rianvasconcelos.s3.amazonaws.com/</downloadPath>
							<skip>false</skip>
							<!-- optional, skips this plugin entirely, use on the command line 
								like -Dmongodb.skip -->
						</configuration>
					</execution>
					<execution>
						<id>stop</id>
						<goals>
							<goal>stop</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
 ```
## Executando o MongoDb embedded
   Vá até a raiz dessa demo(wrapper-flapdoodle-example) e execute `mvn install` ou utilize sua IDE de preferência e exute o maven install. Caso o mongo seja iniciado com sucesso você terá uma saída parecida como isso:
   ```xml
    Download GenericFeatureAwareVersion{2.7.1}:Windows:B64 START
Download GenericFeatureAwareVersion{2.7.1}:Windows:B64 DownloadSize: 134682446
Download GenericFeatureAwareVersion{2.7.1}:Windows:B64 0% 1% 2% 3% 4% 5% 6% 7% 8% 9% 10% 11% 12% 13% 14% 15% 16% 17% 18% 19% 20% 21% 22% 23% 24% 25% 26% 27% 28% 29% 30% 31% 32% 33% 34% 35% 36% 37% 38% 39% 40% 41% 42% 43% 44% 45% 46% 47% 48% 49% 50% 51% 52% 53% 54% 55% 56% 57% 58% 59% 60% 61% 62% 63% 64% 65% 66% 67% 68% 69% 70% 71% 72% 73% 74% 75% 76% 77% 78% 79% 80% 81% 82% 83% 84% 85% 86% 87% 88% 89% 90% 91% 92% 93% 94% 95% 96% 97% 98% 99% 100% Download GenericFeatureAwareVersion{2.7.1}:Windows:B64 downloaded with 21920kb/s
Download GenericFeatureAwareVersion{2.7.1}:Windows:B64 DONE
Extract C:\Users\rndd\.embedmongo\win32\mongodb-win32-x86_64-2.7.1.zip START
Extract C:\Users\rndd\.embedmongo\win32\mongodb-win32-x86_64-2.7.1.zip extract mongodb-win32-x86_64-2.7.1/bin/mongod.exe
Extract C:\Users\rndd\.embedmongo\win32\mongodb-win32-x86_64-2.7.1.zip nothing left
Extract C:\Users\rndd\.embedmongo\win32\mongodb-win32-x86_64-2.7.1.zip DONE
[mongod output]note: noprealloc may hurt performance in many applications
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten] MongoDB starting : pid=15560 port=27017 dbpath=C:\tmp\mongotest 64-bit host=BRPC013301
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten]
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten] ** NOTE: This is a development version (2.7.1) of MongoDB.
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten] **       Not recommended for production.
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten]
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten] targetMinOS: Windows Server 2003 SP2
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten] db version v2.7.1
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten] git version: 11f6d56e9800f1a580b2260af0f051f847dd4431
[mongod output] 2020-11-11T16:44:50.137-0300 [initandlisten] build info: windows sys.getwindowsversion(major=6, minor=1, build=7601, platform=2, service_pack='Service Pack 1') BOOST_LIB_VERSION=1_49
   ```
## Nota
* O plug-in `embedmongo-offline-maven-plugin` torna possível trabalhar com o MongoDB integrado a partir de um diretório local. Remova a tag `<downloadPath>` no seu arquivo `pom.xml`. Acesse https://github.com/rianmachado/embedmongo-offline-maven-plugin para obter informações..  

# Detalhes do embedmongo-maven-plugin (Aprofunde sua leitura) 
Este plug-in permite iniciar e interromper uma instância do MongoDB durante uma compilação do Maven, por exemplo, para teste de integração. A instância do Mongo não é estritamente integrada (não está em execução na JVM de seu aplicativo), mas é uma instância gerenciada que existe apenas durante o tempo de vida de sua construção. [<< Leia Mais >>](https://github.com/joelittlejohn/embedmongo-maven-plugin/blob/master/README.md)
