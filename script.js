async function asd() {
    var carid = Number(document.querySelector("#productId").value);
    var productObj = {};
    
    await fetch(`http://localhost:8081/getCar/${carid}`)
        .then((data) => {
            if (!data.ok) throw new Error("resource not found");
            return data.json();
        })
        .then((res) => {
            productObj = res;
            console.log(productObj);
        })
        .catch((err) => console.log(err));

    function abc() {
        var carDetailsDiv = document.querySelector("#carDetails p");
        
        // Check if the element exists
        if (carDetailsDiv) {
            // Display car details
            carDetailsDiv.innerHTML = `
                <strong>Car Name:</strong> ${productObj.carName}<br>
                <strong>Engine Model:</strong> ${productObj.engineModel}<br>
                <strong>Max Speed:</strong> ${productObj.maxSpeed}
            `;
        } else {
            console.log("carDetails div not found");
        }
    }
    abc();
}

// Handle form submission for car data
// Handle adding a car
document.getElementById('addCarForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    const carData = {
        "carName": document.getElementById('addCarName').value,
        "maxSpeed": document.getElementById('addMaxSpeed').value,
        "engineModel": document.getElementById('addEngineModel').value
    };

    const addButton = document.getElementById('addButton');
    const buttonText = addButton.querySelector('.button-text');
    const spinner = addButton.querySelector('.loading-spinner');

    // Show loading spinner
    buttonText.style.display = "none";
    spinner.style.display = "inline-block";

    try {
        const response = await fetch('http://localhost:8081/saveNewCar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(carData)
        });

        if (!response.ok) throw new Error('Network response was not ok');

        document.getElementById('addMessage').innerText = 'Car added successfully!';

        // Clear the input fields after successful submission
        document.getElementById('addCarName').value = '';
        document.getElementById('addMaxSpeed').value = '';
        document.getElementById('addEngineModel').value = '';

    } catch (error) {
        document.getElementById('addMessage').innerText = 'Error adding car: ' + error;
    } finally {
        // Reset button state
        buttonText.style.display = "inline-block";
        spinner.style.display = "none";
    }
});


// Handle deleting a car
document.getElementById('deleteCarButton').addEventListener('click', async function() {
    const carId = document.getElementById('deleteCarId').value;

    try {
        const response = await fetch(`http://localhost:8081/del/${carId}`, {
            method: 'DELETE'
        });
        if (!response.ok) throw new Error('Network response was not ok');
        document.getElementById('deleteMessage').innerText = 'Car deleted successfully!';
    } catch (error) {
        document.getElementById('deleteMessage').innerText = 'Error deleting car: ' + error;
    }
});

// Fetch all car details
async function fetchAllCars() {
    try {
        const response = await fetch('http://localhost:8081/allRecords'); // Your API endpoint to get all cars
        if (!response.ok) throw new Error('Network response was not ok');

        const cars = await response.json(); // Parse the response as JSON
        displayCars(cars); // Call function to display cars
    } catch (error) {
        console.error('Error fetching cars:', error);
    }
}

// Display cars in the table
function displayCars(cars) {
    const carTableBody = document.getElementById('carTableBody');
    carTableBody.innerHTML = ''; // Clear existing rows

    cars.forEach(car => {
        const row = document.createElement('tr'); // Create a new row
        row.innerHTML = `
            <td>${car.carid}</td>
            <td>${car.carName}</td>
            <td>${car.maxSpeed}</td>
            <td>${car.engineModel}</td>
        `; // Fill the row with car data
        carTableBody.appendChild(row); // Append the row to the table body
    });
}

// Fetch all cars when the button is clicked
document.getElementById('fetchAllCarsButton').addEventListener('click', fetchAllCars);