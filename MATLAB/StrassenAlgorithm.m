% An algorithm which computes 2*2 matrices A and B, 
% using Strassen's Algorithm
% Written by: Ylldrit Miftari

% Create 2*2 Matrix A
A = [1,3;7,5];

% Create 2*2 Matrix B
B = [6,8;4,2];

M1 = (A(1,1) + A(2,2)) * (B(1,1) + B(2,2));
M2 = (A(2,1) + A(2,2)) * B(1,1);
M3 = A(1,1) * (B(1,2) - B(2,2));
M4 = A(2,2) * (B(2,1) - B(1,1));
M5 = (A(1,1) + A(1,2)) * B(2,2);
M6 = (A(2,1) - A(1,1)) * (B(1,1) + B(1,2));
M7 = (A(1,2) - A(2,2)) * (B(2,1) + B(2,2));

% Compute Matrix C
C(1,1) = M1 + M4 - M5 + M7;
C(1,2) = M3 + M5;
C(2,1) = M2 + M4;
C(2,2) = M1- M2 + M3 + M6;

% Print Matrices
fprintf("\nC = A*B, using Strassen's Algorithm: ");
Strassen = C
fprintf("C = A*B, using conventional matrix multiplication: ");
Multiply_AB = A*B
