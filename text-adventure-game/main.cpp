#include <iostream>
#include <string>
#include <vector>
#include <map>
#include "Character.h"

int main() {
    // Create Rooms
    Room startRoom("You are in a dimly lit room.");
    Room hallway("You are in a long hallway.");
    Room treasureRoom("You have entered a treasure room!");
    Room garden("You find yourself in a peaceful garden.");
    Room library("You are surrounded by shelves of books in the library.");
    Room kitchen("You are in a cluttered kitchen.");
    Room observatory("You are in a high-tech observatory.");

    // Define exits between rooms
    startRoom.AddExit("north", &hallway);
    startRoom.AddExit("south", &kitchen);

    hallway.AddExit("north", &treasureRoom);
    hallway.AddExit("east", &garden);
    hallway.AddExit("south", &startRoom);
    hallway.AddExit("west", &library);

    treasureRoom.AddExit("south", &hallway);

    garden.AddExit("west", &hallway);

    library.AddExit("east", &hallway);
    library.AddExit("south", &observatory);

    kitchen.AddExit("north", &startRoom);

    observatory.AddExit("north", &library);

    // Create Items
    Item key("Key", "A shiny key that looks important.");
    Item sword("Sword", "A sharp sword with a golden hilt.");
    Item potion("Potion", "A magical potion to restore health.");
    Item map("Map", "An old map showing hidden treasures.");
    Item telescope("Telescope", "A powerful telescope for stargazing.");

    // Add items to rooms
    startRoom.AddItem(key);
    treasureRoom.AddItem(sword);
    garden.AddItem(potion);
    library.AddItem(map);
    observatory.AddItem(telescope);

    // Create a Player
    Player player("Alice", 100, &startRoom);

    // Set the player's starting location
    player.SetLocation(&startRoom);

    // Game loop (basic interaction)
    while (true) {
        std::cout << "Current Location: " << player.GetLocation()->GetDescription() << std::endl;
        std::cout << "Items in the room:" << std::endl;
        for (const Item& item : player.GetLocation()->GetItems()) {
            std::cout << "- " << item.GetName() << ": " << item.GetDescription() << std::endl;
        }

        std::cout << "Options: ";
        std::cout << "1. Look around | ";
        std::cout << "2. Interact with an item | ";
        std::cout << "3. Add an item to inventory | ";
        std::cout << "4. Remove an item from inventory | ";
        std::cout << std::endl << "5. Move to another room | ";
        std::cout << "6. Quit" << std::endl;

        int choice;
        std::cin >> choice;

        if (choice == 1) {
            // Player looks around (no action required)
            std::cout << "You look around the room." << std::endl;
        }
        // Inside the main game loop, add an option for the player to interact with items
        else if (choice == 2) {
            // Player interacts with an item in the room or inventory
            std::cout << "Enter 'room' to interact with an item in the room, or 'inventory' to interact with an item in your inventory: ";
            std::string location;
            std::cin >> location;

            if (location == "room") {
                std::cout << "Enter the name of the item you want to interact with: ";
                std::string itemName;
                std::cin >> itemName;

                for (Item& item : player.GetLocation()->GetItems()) {
                    if (item.GetName() == itemName) {
                        item.Interact();
                        break;
                    }
                }
            }
            else if (location == "inventory") {
                player.ListInventory();
                std::cout << "Enter the name of the item you want to interact with: ";
                std::string itemName;
                std::cin >> itemName;

                for (Item& item : player.GetInventory()) {
                    if (item.GetName() == itemName) {
                        item.Interact();
                        break;
                    }
                }
            }
            else {
                std::cout << "Invalid location. Try again." << std::endl;
            }
        }
        else if (choice == 3) {
            // Pick up an item from the room
            std::cout << "Enter the name of the item you want to pick up: ";
            std::string itemName;
            std::cin >> itemName;

            // Find the item in the current room
            bool found = false;
            for (Item& item : player.GetLocation()->GetItems()) {
                if (item.GetName() == itemName) {
                    // Add the item to the player's inventory
                    player.AddToInventory(item);
                    // Remove the item from the room
                    player.GetLocation()->RemoveItem(item);
                    std::cout << "You picked up the " << itemName << " and added it to your inventory." << std::endl;
                    found = true;
                    break;
                }
            }
            if (!found) {
                std::cout << "The item is not in the room." << std::endl;
            }
        }
        else if (choice == 4) {
            // Remove an item from the inventory and place it in the room
            std::cout << "Enter the name of the item you want to remove from your inventory: ";
            std::string itemName;
            std::cin >> itemName;

            // Find the item in the player's inventory
            bool found = false;
            for (Item& item : player.GetInventory()) {
                if (item.GetName() == itemName) {
                    // Add the item to the current room
                    player.GetLocation()->AddItem(item);
                    // Remove the item from the player's inventory
                    player.RemoveFromInventory(item);
                    std::cout << "You removed the " << itemName << " from your inventory and placed it in the room." << std::endl;
                    found = true;
                    break;
                }
            }
            if (!found) {
                std::cout << "The item is not in your inventory." << std::endl;
            }
        }
        else if (choice == 5) {
            // Player moves to another room
            std::cout << "Enter the direction (e.g., north, east, south, west): ";
            std::string direction;
            std::cin >> direction;

            Room* nextRoom = player.GetLocation()->GetExit(direction);
            if (nextRoom != nullptr) {
                player.SetLocation(nextRoom);
                std::cout << "You move to the next room." << std::endl;
            }
            else {
                std::cout << "You can't go that way." << std::endl;
            }
        }
        else if (choice == 6) {
            // Quit the game
            std::cout << "Goodbye!" << std::endl;
            break;
        }
        else {
            std::cout << "Invalid choice. Try again." << std::endl;
        }
    }

    return 0;
}

