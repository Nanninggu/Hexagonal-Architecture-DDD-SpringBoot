### Postgresql 세팅 방법
jdbc URL : jdbc:postgresql://localhost:5433/PracticeDB

### 테이블 생성 쿼리
```sql
CREATE TABLE IF NOT EXISTS public.chat_message
(
    id integer NOT NULL DEFAULT nextval('chat_message_id_seq'::regclass),
    message text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT chat_message_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.chat_message
    OWNER to postgres;
```
```sql
CREATE TABLE IF NOT EXISTS public.entities
(
    id integer NOT NULL DEFAULT nextval('entities_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT entities_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.entities
    OWNER to postgres;
```
```sql
CREATE TABLE IF NOT EXISTS public.posts
(
    id integer NOT NULL DEFAULT nextval('posts_id_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author character varying(255) COLLATE pg_catalog."default" NOT NULL,
    date date NOT NULL,
    views integer NOT NULL,
    content text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT posts_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.posts
    OWNER to postgres;
```
```sql
CREATE TABLE IF NOT EXISTS public.received_message
(
    id integer NOT NULL DEFAULT nextval('received_message_id_seq'::regclass),
    message text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT received_message_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.received_message
    OWNER to postgres;
```
```sql
CREATE TABLE IF NOT EXISTS public.tourist_spot
(
    id integer NOT NULL DEFAULT nextval('tourist_spot_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tourist_spot_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tourist_spot
    OWNER to postgres;
```
```sql
CREATE TABLE IF NOT EXISTS public.flight_booking
(
    id integer NOT NULL DEFAULT nextval('flight_booking_id_seq'::regclass),
    user_id character varying(50) COLLATE pg_catalog."default" NOT NULL,
    flight_id character varying(50) COLLATE pg_catalog."default" NOT NULL,
    seat_class character varying(20) COLLATE pg_catalog."default" NOT NULL,
    seat_number character varying(10) COLLATE pg_catalog."default" NOT NULL,
    status character varying(20) COLLATE pg_catalog."default" NOT NULL,
    payment_status character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT flight_booking_pkey PRIMARY KEY (seat_number)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.flight_booking
    OWNER to postgres;
```

```sql
CREATE TABLE IF NOT EXISTS public.flight_booking_request
(
    id integer NOT NULL DEFAULT nextval('flight_booking_request_id_seq'::regclass),
    user_id character varying(50) COLLATE pg_catalog."default" NOT NULL,
    flight_id character varying(50) COLLATE pg_catalog."default" NOT NULL,
    seat_class character varying(20) COLLATE pg_catalog."default" NOT NULL,
    seat_number character varying(10) COLLATE pg_catalog."default" NOT NULL,
    payment_details text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT flight_booking_request_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.flight_booking_request
    OWNER to postgres;
```

### 프로젝트 구조 설명

프로젝트는 헥사고날 아키텍처와 도메인 주도 설계(DDD)를 기반으로 구성되어 있다. 각 폴더는 특정한 역할을 담당하며, 다음과 같은 구조를 가진다:

```
project-root/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── api/                    # 어댑터: 외부와의 인터페이스 (Controller)
│   │   │           │   └── ReceivedMessageController.java
│   │   │           ├── application/            # 애플리케이션 서비스: 비즈니스 로직
│   │   │           │   └── ReceivedMessageService.java
│   │   │           ├── domain/                 # 도메인 모델: 핵심 비즈니스 로직과 엔티티
│   │   │           │   └── ReceivedMessage.java
│   │   │           ├── infrastructure/         # 인프라스트럭처: 데이터베이스, 외부 시스템과의 통신
│   │   │           │   └── tcp_ip/
│   │   │           │       ├── MyBatisUtil.java
│   │   │           │       ├── ReceivedMessageMapper.java
│   │   │           │       └── TcpServer.java
│   │   │           └── config/                 # 설정: 애플리케이션 설정 파일
│   │   │               └── WebSocketConfig.java
│   │   ├── resources/
│   │   │   ├── mybatis/
│   │   │   │   └── mappers/
│   │   │   │       └── ReceivedMessageMapper.xml
│   │   │   ├── templates/
│   │   │   │   └── messages.html
│   │   │   └── mybatis-config.xml
│   │   └── HxProjectApplication.java           # 메인 애플리케이션 클래스
├── build.gradle
└── README.md
```

### 각 폴더의 역할

- **api**: 외부와의 인터페이스를 담당하는 어댑터 계층입니다. 주로 컨트롤러가 위치한다.
- **application**: 애플리케이션 서비스 계층으로, 비즈니스 로직을 포함합니다. 도메인 모델을 사용하여 작업을 수행한다.
- **domain**: 도메인 모델 계층으로, 핵심 비즈니스 로직과 엔티티가 위치한다.
- **infrastructure**: 인프라스트럭처 계층으로, 데이터베이스와 외부 시스템과의 통신을 담당한다.
- **config**: 애플리케이션 설정 파일이 위치한다.
- **resources**: 리소스 파일들이 위치합니다. MyBatis 매퍼 XML 파일과 Thymeleaf 템플릿 파일이 포함된다.

### 이 프로젝트의 장점
#### 설명

1. **유지보수 용이성**:
    - **api**: 외부와의 인터페이스를 담당하는 계층이 분리되어 있어, UI나 API 변경 시 다른 계층에 영향을 주지 않고 수정할 수 있다.
    - **application**: 비즈니스 로직이 한 곳에 모여 있어, 로직 변경 시 도메인 모델이나 인프라스트럭처에 영향을 주지 않고 수정할 수 있다.
    - **domain**: 핵심 비즈니스 로직과 엔티티가 독립적으로 관리되어, 비즈니스 규칙 변경 시 다른 계층에 영향을 주지 않고 수정할 수 있다.
    - **infrastructure**: 데이터베이스나 외부 시스템과의 통신이 분리되어 있어, 인프라 변경 시 다른 계층에 영향을 주지 않고 수정할 수 있다.

2. **테스트 용이성**:
    - 각 계층이 독립적으로 분리되어 있어, 단위 테스트 작성이 용이하다. 예를 들어, 도메인 계층의 비즈니스 로직을 테스트할 때 인프라스트럭처 계층의 의존성을 모킹(mocking)하여 테스트할 수 있다.

3. **확장성**:
    - 새로운 기능 추가 시, 해당 기능이 속하는 계층에만 코드를 추가하면 되므로 확장이 용이하다. 예를 들어, 새로운 API 엔드포인트를 추가할 때, `api` 계층에만 코드를 추가하면 된다.

4. **재사용성**:
    - 비즈니스 로직이 `application` 계층에 모여 있어, 다른 프로젝트나 모듈에서 재사용하기 용이하다. 예를 들어, 동일한 비즈니스 로직을 사용하는 다른 애플리케이션에서 `application` 계층을 재사용할 수 있다.

5. **의존성 역전 원칙(DIP) 준수**:
    - 상위 계층이 하위 계층에 의존하지 않고, 인터페이스를 통해 의존성을 주입받아 DIP를 준수한다. 예를 들어, `application` 계층이 `domain` 계층의 인터페이스를 통해 의존성을 주입받는다.

이러한 장점들은 헥사고날 아키텍처와 도메인 주도 설계(DDD)를 기반으로 한 구조에서 얻을 수 있는 대표적인 이점들이다.

