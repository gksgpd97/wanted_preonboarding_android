### 원티트 프리온보딩 Android 코스 편 선발과제
# NewsApi를 활용한 '뉴스똑똑' 모바일 어플리케이션

<h5>
    <img src="https://user-images.githubusercontent.com/35549958/189532325-07f09fa7-6e9b-4be8-8217-a8f0737e37ba.png" width="100px" ></img> 
    >> 뉴스가 '똑똑~' 하고 찾아온다. 뉴스를 읽으며 똑똑해진다.
</h5>

## main branch 구현 내용
- https://whispering-freedom-0c9.notion.site/Android-42d5962bf4ff426dbae32abb4c7c101d
- 위 링크의 프로젝트 요구사항 모두 구현 완료
- mvvm 패턴의 아키텍처 사용
- livedata, navigation, retrofit2, okhttp3, room, glide 사용 

```bash
├── main
│   ├── adpater
│   ├── api
│   ├── data
│   ├── ui
│   ├── utilities
│   └── viewmodels
├── res
``` 
- viewmodel에서 livedata를 통해 최신 데이터 유지
- roomdatabase를 이용해 스크랩한 기사 데이터를 저장 및 삭제
- jetpack navigation으로 여러 framgment의 전환을 용이하게 함

## 그 외 paging branch 구현 내용
- paging3와 hilt를 이용해 무한 스크롤 구현 목표
- 의존성 주입 개념을 처음 접해서 더 공부 후 정리 필요

## 실행 화면
https://user-images.githubusercontent.com/35549958/189533185-514c1c91-f152-4e81-8038-dd6b4229400c.mp4




