// JavaScript code for the online food delivery system

document.getElementById('payment-form').addEventListener('submit', function(event) {
  event.preventDefault(); // Prevent form submission

  // Simulate payment processing
  setTimeout(function() {
    // Show payment success popup box
    document.getElementById('payment-success-modal').classList.remove('hidden');
  }, 2000); // Delay for demonstration purposes, replace with actual payment processing logic
});

// Close the payment success popup box
document.querySelector('.close').addEventListener('click', function() {
  document.getElementById('payment-success-modal').classList.add('hidden');
});
