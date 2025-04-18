# 🎬 Retrofit + Hilt 기본기 실습 앱

Retrofit2와 Hilt를 활용한 모던 안드로이드 네트워크 통신 구조 학습용 프로젝트입니다.  
영화진흥위원회 오픈 API를 통해, 특정 날짜 기준 개봉일자 1위부터 10위까지의 영화 데이터를 조회합니다.

---

## 📦 기술 스택

| 분야        | 사용 기술                          |
|-------------|-------------------------------------|
| 아키텍처    | MVVM + Clean Architecture           |
| 네트워크    | Retrofit2 + GsonConverter           |
| DI          | Hilt                                |
| 비동기 처리 | Kotlin Coroutines                   |
| UI          | XML + RecyclerView + ViewBinding    |
| 기타        | OkHttp Interceptor (로깅)           |

---

## ✨ 주요 기능

- **Retrofit2 기반 REST API 연동**
  - 영화진흥위원회 오픈 API 호출
  - 날짜 쿼리를 통해 박스오피스 TOP 10 데이터 수신

- **Hilt를 통한 의존성 주입 구성**
  - Repository, API Service, ViewModel 등 DI 구성

- **RecyclerView + ViewBinding을 통한 목록 출력**
  - 간결한 XML UI 구성

- **코루틴을 통한 비동기 처리**
  - Main-safe하게 API 호출 및 결과 처리
