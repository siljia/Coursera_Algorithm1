# HW1
## union-find 算法
union-find解决的问题是给定两个元素的index判断二者是否相连。相连具有三个性质：
自反性：p和p相连
对称性：p，q等价于q,p
传递性: if p-q,q-r 则 p-r

三种方法和时间复杂度

## HW

用蒙特卡洛模拟Percolation:Monte Carlo simulations are used to model the probability of different outcomes in a process that cannot easily be predicted due to the intervention of random variables.

### Make use of WeightedQuickUnionUF：
#### 1.Index range and transfer from two dimensions to one dimension. 
weightedQuickUnionUF is based on array, which is one dimensional data structure. And index range is [0,n-1]; But Percolation is a matrix like data structure, two dimensional array. And input is (row,column); range is [1,n] respectively.
两个思路：
1. 建立n*n数组，对输入坐标分别-1
2. n+1*n+1，不特殊处理输入坐标

#### 2.Time complexity of Percolates()
Need a virtual top and a virtual bottom to decrease time complexity when decide percolation appears or not. Otherwise,  traverses in the first row and the last row are needed. The virtual top and virtual bottom can't be near with other grids.

#### 3.BackWash problem
#### 4.Mark state
need a two dimensional byte array to mark state of each grid.





