package com.example.itemservice.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

  ItemRepository itemRepository = new ItemRepository();

  @AfterEach
  void tearDown() {
    itemRepository.clearStore();
  }

  @Test
  void save() {
    // given
    Item item = new Item("foo", 1, 1);

    // when
    itemRepository.save(item);

    // then
    Item foundItem = itemRepository.findById(item.getId());
    assertThat(foundItem).isEqualTo(item);
  }

  @Test
  void findAll() {
    // given
    Item foo = new Item("foo", 1, 1);
    Item bar = new Item("bar", 1, 1);
    itemRepository.save(foo);
    itemRepository.save(bar);

    // when
    List<Item> items = itemRepository.findAll();

    // then
    assertThat(items)
        .containsExactly(foo, bar)
        .hasSize(2);
  }

  @Test
  void update() {
    // given
    Item item = new Item("foo", 1, 1);
    Item savedItem = itemRepository.save(item);
    Long id = savedItem.getId();

    // then
    Item updateParam = new Item("bar", 1, 1);
    itemRepository.update(id, updateParam);
    Item foundItem = itemRepository.findById(id);

    // then
    assertThat(foundItem.getName()).isEqualTo(updateParam.getName());
    assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
    assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
  }

}