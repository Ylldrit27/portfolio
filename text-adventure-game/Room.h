#include <iostream>
#include <string>
#include <map>
#include <vector>
#include "Item.h"

// Forward declaration of Item class
class Item;

class Room {
private:
    std::string description;
    std::map<std::string, Room*> exits; // Mapping directions to adjacent rooms
    std::vector<Item> items;

public:
    // Constructor
    Room(const std::string& desc) : description(desc) {}

    // Function to add an exit to the room
    void AddExit(const std::string& direction, Room* adjacentRoom) {
        exits[direction] = adjacentRoom;
    }

    // Function to get the next room based on a given direction
    Room* GetExit(const std::string& direction) const {
        auto it = exits.find(direction);
        return (it != exits.end()) ? it->second : nullptr;
    }

    // Function to add an item to the room
    void AddItem(const Item& item) {
        items.push_back(item);
    }

    // Function to remove an item from the room
    void RemoveItem(const Item& item) {
        // Find the item in the vector and erase it
        for (auto it = items.begin(); it != items.end(); ++it) {
            if (*it == item) {
                items.erase(it);
                break; // Exit loop once item is removed
            }
        }
    }

    // Function to get the description of the room
    std::string GetDescription() const {
        return description;
    }

    // Function to get the exits of the room
    std::map<std::string, Room*> GetExits() const {
        return exits;
    }

    // Function to get the items in the room
    std::vector<Item> GetItems() const {
        return items;
    }

};
