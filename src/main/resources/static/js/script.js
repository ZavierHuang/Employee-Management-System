document.addEventListener("DOMContentLoaded", () => {
    const toggleButton = document.getElementById("toggleView");
    const tableView = document.getElementById("tableView");
    const cardView = document.getElementById("cardView");
    const savedView = localStorage.getItem("viewPreference");

    if (!toggleButton) {
        console.error("Element with ID 'toggleView' not found!");
        return;
    }

    console.log(toggleButton, tableView, cardView, savedView)

    if (savedView === "card")
        showCardView();
    else
        showTableView();

    toggleButton.addEventListener("click", () => {
        if (tableView.classList.contains("d-none")) {
            showTableView();
            localStorage.setItem("viewPreference", "table");
        } else {
            showCardView();
            localStorage.setItem("viewPreference", "card");
        }
    });

    // 顯示 Table View
    function showTableView() {
        tableView.classList.remove("d-none");
        cardView.classList.add("d-none");
        toggleButton.textContent = "Switch to Card View";
    }

    // 顯示 Card View
    function showCardView() {
        tableView.classList.add("d-none");
        cardView.classList.remove("d-none");
        toggleButton.textContent = "Switch to Table View";
    }
});

