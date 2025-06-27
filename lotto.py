import numpy as np
from collections import Counter

# Extract the 6 winning numbers from each row
winning_numbers = df[['Winning Number 1', '2', '3', '4', '5', '6']]

# Convert all values to numeric (in case any are strings)
winning_numbers = winning_numbers.apply(pd.to_numeric, errors='coerce')

# Flatten to one list and count frequency
all_numbers = winning_numbers.values.flatten()
number_counts = Counter(all_numbers)

# Get the 20 most common numbers
most_common_numbers = [num for num, count in number_counts.most_common(20)]

# Generate 5 sets of predicted numbers by randomly choosing from the top 20
np.random.seed(42)  # for reproducibility
predicted_sets = [sorted(np.random.choice(most_common_numbers, 6, replace=False)) for _ in range(5)]

predicted_sets
