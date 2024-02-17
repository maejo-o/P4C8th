// var: 지역변수 개념으로 함수범위에서 유효, 선언하지 않을 시 자동으로 전역변수
var foo = 'foo1'; 
console.log(foo); // foo1
 
if (true) {
  var foo = 'foo2';
  console.log(foo); // foo2
}

console.log(foo); // foo2


// let: 값의 재할당이 가능
// const: 재할당 불가능, 배열값의 변경, 추가, 객체의 필드 변경등은 가능
let foo = 'foo1';
const bar = 'bar1';
console.log(foo); // foo1
 
if (true) {
  let foo = 'foo2';
  console.log(foo); // foo2
  console.log(bar); // bar1
}
 
console.log(foo); // foo1
bar = 'bar2'; // error