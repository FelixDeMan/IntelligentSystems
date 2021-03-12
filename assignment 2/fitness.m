function y = fitness(x)


    for i = 2:size(x): 
        y = y + (x(i-1)^2 - x(i)) ^2 + (x(3)^2 - x(4)) ^2 + (x(2)^2 - x(3)) ^2 + (x(1)^2 - x(2)) ^2 + (1 - x(1))^2;
    end
    
end

