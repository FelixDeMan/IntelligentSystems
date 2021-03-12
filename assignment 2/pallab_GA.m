function [x,fval,exitflag,output,population,score] = pallab_GA(nvars,lb,ub,PopulationSize_Data,InitialPopulation_Data)
%% This is an auto generated MATLAB file from Optimization Tool.

%% Created by Farshad Jokar _ Shiraz University

%% Start with the default options
options = gaoptimset;

%% 
nvars=3;
lb=[6 1 1];
ub=[12 3 5];
PopulationSize_Data=100;
InitialPopulation_Data=[6.003 1.001 4.999];
%% Modify options setting
options = gaoptimset(options,'PopulationSize', PopulationSize_Data);
options = gaoptimset(options,'InitialPopulation', InitialPopulation_Data);
options = gaoptimset(options,'Display', 'off');
options = gaoptimset(options,'PlotFcns', {  @gaplotbestf @gaplotbestindiv });
[x,fval,exitflag,output,population,score] = ...
ga(@pallab,nvars,[],[],[],[],lb,ub,[],[],options);
