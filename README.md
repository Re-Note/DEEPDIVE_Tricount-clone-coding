### [DEEPDIVE] 실습 과제 12.Tricount 클론 코딩

### [기본 구현 요구 사항]

- Tricount라는 정산 애플리케이션의 서버 파트를 클론코딩 진행.

- 기능 요구사항
  1. 회원가입: 유저 고유 키, 아이디/비밀번호, 닉네임.
  2. 정산: 유저별 여러 정산 생성 가능, 참가자만 정산 열람 가능.
  3. 지출: ID, 이름, 유저, 금액, 날짜로 구성되며, 정산과 1:N 관계.
  4. 정산 결과: 참가자 간 송금 내역 제공, 같은 금액 지출 시 빈 결과 반환.

- 구현 요구사항
  1. 스프링 부트, AutoConfiguration, H2 DB, JdbcTemplate 사용.
  2. 실행 가능한 JAR 패키징, JSON 통신.
  3. 금액은 BigDecimal로 처리하며 1원 단위 반올림.
  4. Create, Read, Delete만 구현.

- 제공된 API 목록을 참고하여 구현합니다.
  
- Postman 또는 메모장을 사용하여 항목들을 확인하며 진행합니다.

- 위 기능을 구현한 executable jar 파일
  
---
