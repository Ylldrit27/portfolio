#include <iostream>
#include <string>
#include <vector>
#include "Room.h"

// Forward declaration of Item class
class Item;

// Forward declaration of Room class
class Room;

class Character {
private:
    std::string name;
    int health;
    std::vector<Item> inventory;

public:
    // Constructor
    Character(const std::string& name, int health) : name(name), health(health) {}

    // Function to take damage
    void TakeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
};

class Player : public Character {
private:
    Room* location; // Current room of the player
    std::vector<Item> inventory; // Player's inventory

public:
    // Constructor
    Player(const std::string& name, int health, Room* startLocation) : Character(name, health), location(startLocation) {}

    // Function to set the player's location
    void SetLocation(Room* newLocation) {
        location = newLocation;
    }

    // Function to get the current location of the player
    Room* GetLocation() const {
        return location;
    }

    // Function to move the player to a different room
    void MoveTo(Room* newLocation) {
        location = newLocation;
    }

    // Function to add an item to the player's inventory
    void AddToInventory(const Item& item) {
        inventory.push_back(item);
    }

    // Function to remove an item from the player's inventory
    void RemoveFromInventory(const Item& item) {
        // Find the item in the inventory and erase it
        for (auto it = inventory.begin(); it != inventory.end(); ++it) {
            if (*it == item) {
                inventory.erase(it);
                break; // Exit loop once item is removed
            }
        }
    }

    std::vector<Item>& GetInventory() {
        return inventory;
    }

    // Function to list items in the player's inventory
    void ListInventory() const {
        std::cout << "Inventory:" << std::endl;
        for (const Item& item : inventory) {
            std::cout << "- " << item.GetName() << std::endl;
        }
    }

};