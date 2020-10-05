package hello.hellospring.domain;

public class Member {
    private Long id;//시스템이 정하는 id, 고객이 정하지 않음
    private String name;

    //getter와 setter는 가장 단순한 구조로 파라미터를 전달하는 다른 방법들이 많음
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
