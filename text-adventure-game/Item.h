#include <iostream>
#include <string>

class Item {
private:
    std::string name;
    std::string description;

public:
    // Constructor
    Item(const std::string& name, const std::string& desc) : name(name), description(desc) {}

    // Function to get the description of the item
    std::string GetDescription() const {
        return description;
    }

    // Function to describe the interaction with the item
    void Interact() const {
        std::cout << "You interact with the " << name << ". " << description << std::endl;
    }

    // Equality operator
    bool operator==(const Item& other) const {
        return (name == other.name && description == other.description);
    }

    // Function to get the name of the item
    std::string GetName() const {
        return name;
    }

};
