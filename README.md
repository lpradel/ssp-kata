:scissors: Schere :rock: Stein :scroll: Papier Kata
==================
![example workflow](https://github.com/lpradel/ssp-kata/actions/workflows/maven.yml/badge.svg)

## Installation

### Maven

```shell
mvn clean install -P integration-test
```

## Usage

In the directory `src\test\docker` start the service with docker-compose:

```shell
docker-compose up
```

The service will serve requests at `http://localhost:8080`, e.g.:

```shell
GET http://localhost:8080/actuator/mappings
```

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :)

## Credits

- [Lukas Pradel](https://github.com/lpradel)

## License


    Copyright 2022 Lukas Pradel
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
