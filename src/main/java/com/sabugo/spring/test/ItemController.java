package com.sabugo.spring.test;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ItemController {
    private final ItemRepository repo;

    /*•	List of the inventory items list (item no, name, amount, inventory code)
•	Read item details (by item no)
•	Withdrawal quantity of a specific item from stock
•	Deposit quantity of a specific item to stock
•	Add item to stock
•	Delete an item from stock
*/
    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    // Aggregate root

    //get all items: curl -v localhost:8080/items
    @GetMapping("/items")
    List<Item> getAllItems() {
        return repo.findAll();
    }

    //add a single item: curl -X POST localhost:8080/items -H 'Content-type:application/json' -d amount=15 -d name=samwise -d code=543213 -d num=100
    @PostMapping("/items")
    Item addItem(@RequestParam Map<String, String> body/*@RequestBody Item newItem*/) {
        Item newItem = new Item(Long.parseLong(body.get("num")), body.get("name"), Integer.parseInt(body.get("amount")),
                Integer.parseInt(body.get("code")));
        return repo.save(newItem);
    }

    // Single item
    //get a single item based on id
    @GetMapping("/items/{id}")
    Item getItemByID(@PathVariable Long id) {

        return repo.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
    //withdraw given amount: curl -X POST localhost:8080/withdraw/1 -H 'Content-type:application/json' -d amount=15
    @PostMapping("/withdraw/{id}")
    Item withdrawItem(@RequestParam Map<String, String> body, @PathVariable Long id) {
        int amount = Integer.parseInt(body.get("amount"));
        return repo.findById(id)
                .map(item -> {
                    item.setAmount(
                            (item.getAmount() - amount) < 0 ? 0 : item.getAmount() - amount) ;
                    return repo.save(item);
                })
                .orElseThrow(()->new ItemNotFoundException(id));

    }

    @PutMapping("/deposit/{id}")
    Item depositItem(@RequestParam Map<String, String> body, @PathVariable Long id ){
        int amount = Integer.parseInt(body.get("amount"));
        return repo.findById(id)
                .map(item -> {
                    item.setAmount(item.getAmount() + amount) ;
                    return repo.save(item);
                })
                .orElseThrow(()->new ItemNotFoundException(id));
    }
    //delete item
    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
