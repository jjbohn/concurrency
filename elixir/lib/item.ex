defmodule Item do
  @derive [Poison.Encoder]
  defstruct [:by, :deleted, :kids, :parent, :title, :type, :score, :time, :descendants, :id]
end
