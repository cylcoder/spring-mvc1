package com.example.itemservice.web.item.basic;

import com.example.itemservice.domain.item.Item;
import com.example.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

  private final ItemRepository itemRepository;

  @PostConstruct
  public void init() {
    itemRepository.save(new Item("foo", 1, 1));
    itemRepository.save(new Item("bar", 1, 1));
  }

  @GetMapping
  public String items(Model model) {
    List<Item> items = itemRepository.findAll();
    model.addAttribute("items", items);
    return "basic/items";
  }

  @GetMapping("/{itemId}")
  public String item(@PathVariable("itemId") Long id, Model model) {
    Item item = itemRepository.findById(id);
    model.addAttribute("item", item);
    return "basic/item";
  }

  @GetMapping("/add")
  public String addForm() {
    return "basic/addForm";
  }

//  @PostMapping("/add")
  public String addItemV1(String itemName, int price, Integer quantity, Model model) {
    Item item = new Item(itemName, price, quantity);
    itemRepository.save(item);
    model.addAttribute("item", item);
    return "basic/item";
  }

//  @PostMapping("/add")
  public String addItemV2(@ModelAttribute("item") Item item, Model model) {
    itemRepository.save(item);
//    model.addAttribute("item", item); // 자동 추가, 생략 가능
    return "basic/item";
  }

  /*
   * @ModelAttribute name 생략 가능
   * model.addAttribute(item); 자동 추가, 생략 가능
   * 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
   */
//  @PostMapping("/add")
  public String addItemV3(@ModelAttribute  Item item) {
    itemRepository.save(item);
    return "basic/item";
  }

  /*
   * @ModelAttribute 자체 생략 가능
   * model.addAttribute(item) 자동 추가
   */
  @PostMapping("/add")
  public String addItemV4(Item item) {
    itemRepository.save(item);
    return "basic/item";
  }

}
