/*
* @author Jeet Prakash
* */
export type Response = {
  status: boolean,
  message: string,
  data: Test | Item[]
};

export type Item = {
  name: string,
  directory: boolean,
  isRoot: boolean,
  rootAlias: string,
  path: string
};

export type Test = {
  message: string
}