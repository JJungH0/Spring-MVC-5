package hello.itemservice.domain.item;


import lombok.Getter;

public enum ItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    @Getter
    private final String description;

    ItemType(String description) {
        this.description = description;
    }
}
